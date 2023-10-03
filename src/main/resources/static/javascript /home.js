const expenseContainer = document.getElementById("expense-table-body");
const title = document.getElementById("title-input");
const category = document.getElementById("category-input");
const amount = document.getElementById("amount-input");
const date = document.getElementById("date-input");
const form = document.getElementById("expense-form");
const logoutButton = document.getElementById("logout-button");

const headers = {
    'Content-Type': 'application/json'
};

const baseUrl = 'http://localhost:8080/expense';

form.addEventListener("submit", handleSubmit);
logoutButton.addEventListener("click", handleLogout);

async function handleSubmit(e) {
    e.preventDefault();
    let bodyObj = {
        title: title.value,
        category: category.value,
        paymentAmount: parseFloat(amount.value),
        date: date.value
    };
    await addExpense(bodyObj);
    form.reset();
}

async function addExpense(expenseData) {
    const response = await fetch(baseUrl, {
        method: "POST",
        body: JSON.stringify(expenseData),
        headers: headers
    }).catch(err => console.error(err.message));

    if (response.status === 200) {
        getExpenses();
        alert("Expense created successfully!"); // Alert after creating an expense
    } else {
        alert("Failed to create expense!"); // Alert if creating expense fails
    }
}

async function getExpenses() {
    const response = await fetch(baseUrl, {
        method: "GET",
        headers: headers
    }).catch(err => console.error(err));

    if (response.status === 200) {
        const data = await response.json();
        createExpenseRows(data);
    }
}

function createExpenseRows(expenses) {
    expenses.sort((a, b) => new Date(a.date) - new Date(b.date));

    expenseContainer.innerHTML = "";
    expenses.forEach(expense => {
        const expenseDate = new Date(expense.date);

        const day = String(expenseDate.getDate()).padStart(2, '0');
        const month = String(expenseDate.getMonth() + 1).padStart(2, '0');
        const year = expenseDate.getFullYear();

        const formattedDate = `${day}/${month}/${year}`;

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${expense.title}</td>
            <td>${expense.category}</td>
            <td>${expense.paymentAmount}</td>
            <td>${formattedDate}</td> <!-- Display the formatted date -->
            <td>
                <button class="btn btn-info" data-expense-id="${expense.id}" onclick="handleUpdate(this)">Update</button>
                <button class="btn btn-danger" onclick="deleteExpense(${expense.id})">Delete</button>
            </td>
        `;
        expenseContainer.appendChild(row);
    });
}

async function deleteExpense(expenseId) {
    const response = await fetch(`${baseUrl}/${expenseId}`, {
        method: "DELETE",
        headers: headers
    }).catch(err => console.error(err));

    if (response.status === 200) {
        getExpenses();
    }
}

function handleUpdate(button) {
    const expenseId = button.getAttribute("data-expense-id");

    console.log(expenseId);

    const updatedExpenseData = {
        id: expenseId,
        title: title.value,
        category: category.value,
        paymentAmount: parseFloat(amount.value),
        date: date.value
    };

    console.log(updatedExpenseData);
    console.log(JSON.stringify(updatedExpenseData));

    fetch(`${baseUrl}/`, {
        method: "PUT",
        body: JSON.stringify(updatedExpenseData),
        headers: headers
    })
        .then(response => {
            if (response.status === 200) {
                form.reset();
                getExpenses();
                alert("Expense updated successfully!");
            } else {
                console.error("Failed to update expense");
                alert("Failed to update expense!");
            }
        })
        .catch(err => console.error(err))
        .finally(() => {
            title.disabled = false;
            category.disabled = false;
            amount.disabled = false;
            date.disabled = false;

            form.removeEventListener("submit", handleUpdate);
            form.addEventListener("submit", handleSubmit);
            const addButton = form.querySelector("button[type='submit']");
            addButton.textContent = "Add Expense";
        });
}

function handleLogout() {
    window.location.href = "login.html";
}

getExpenses();
