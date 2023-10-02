const expenseContainer = document.getElementById("expense-table-body");
const title = document.getElementById("title-input");
const category = document.getElementById("category-input");
const amount = document.getElementById("amount-input");
const date = document.getElementById("date-input");
const form = document.getElementById("expense-form");
const logoutButton = document.getElementById("logout-button"); // Get the logout button element

const headers = {
    'Content-Type': 'application/json'
};

const baseUrl = 'http://localhost:8080/expense';

form.addEventListener("submit", handleSubmit);
logoutButton.addEventListener("click", handleLogout); // Add a click event listener to the logout button

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
    // Sort expenses by date in ascending order
    expenses.sort((a, b) => new Date(a.date) - new Date(b.date));

    expenseContainer.innerHTML = "";
    expenses.forEach(expense => {
        // Convert the date string to a JavaScript Date object
        const expenseDate = new Date(expense.date);

        // Get the day, month, and year components
        const day = String(expenseDate.getDate()).padStart(2, '0');
        const month = String(expenseDate.getMonth() + 1).padStart(2, '0');
        const year = expenseDate.getFullYear();

        // Create the formatted date string
        const formattedDate = `${day}/${month}/${year}`;

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${expense.title}</td>
            <td>${expense.category}</td>
            <td>${expense.paymentAmount}</td>
            <td>${formattedDate}</td> <!-- Display the formatted date -->
            <td>
                <button class="btn btn-info" onclick="editExpense(${expense.id})">Update</button>
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

function editExpense(expenseId) {
    fetch(`${baseUrl}/${expenseId}`, {
        method: "GET",
//        mode: 'no-cors'
    })
        .then(response => {
            if (response.status === 404) {
                console.error("Expense not found.");
            } else if (response.ok) {
                return response.json();
            } else {
                console.error("Failed to fetch expense data.");
            }
        })
        .then(data => {
            if (data) {
                // Populate the form fields with the fetched expense data
                title.value = data.title;
                category.value = data.category;
                amount.value = data.paymentAmount;
                date.value = data.date;

                // Add an event listener to the form for updating the expense
                form.removeEventListener("submit", handleSubmit);
                form.addEventListener("submit", (e) => handleUpdate(e, expenseId));
                const addButton = form.querySelector("button[type='submit']");
                addButton.textContent = "Update Expense";
            }
        })
        .catch(err => console.error(err));
}

function handleUpdate(e, expenseId) {
    e.preventDefault();
    const updatedExpenseData = {
        title: title.value,
        category: category.value,
        paymentAmount: parseFloat(amount.value),
        date: date.value
    };

    // Send a PUT request to update the expense with the new data
    fetch(`${baseUrl}/${expenseId}`, {
        method: "PUT",
        body: JSON.stringify(updatedExpenseData),
        headers: headers
    })
        .then(response => {
            if (response.status === 200) {
                // Reset the form and reload the expenses
                form.reset();
                getExpenses();
            } else {
                console.error("Failed to update expense");
            }
        })
        .catch(err => console.error(err));
}

function handleLogout() {
    // Redirect the user to the login page
    window.location.href = "login.html"; // Replace "login.html" with the actual URL of your login page
}

// Load expenses when the page loads
getExpenses();
