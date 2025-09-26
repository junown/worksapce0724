let transactions = JSON.parse(localStorage.getItem('transactions')) || [];
let filterState = 'all';
let currentTransactionType = 'income';

const transactionList = document.getElementById('account-list');
const descriptionInput = document.getElementById('account-detail');
const amountInput = document.getElementById('account-money');
const balanceDisplay = document.getElementById('account-remain');
const filterBtns = document.querySelectorAll('.filter-buttons button');
const incomeBtn = document.getElementById('money-inbtn');
const expenseBtn = document.getElementById('money-outbtn');
const accountContainer = document.getElementById('account-container');

function init(){
    bindEvents();
    render();
}

function bindEvents(){
    const addBtn = document.getElementById('account-addbtn');
    addBtn.addEventListener('click', addTransaction);

    amountInput.addEventListener('keydown', function(e){
        if (e.key =='Enter'){
            addTransaction();
        }
    });

    incomeBtn.addEventListener('click', () => setTransactionType('income'));
    expenseBtn.addEventListener('click', () => setTransactionType('expense'));

    filterBtns.forEach(function(btn){
        btn.addEventListener('click', function(ev){
            setFilter(ev.target.dataset.filter);
        });
    });
}

function addTransaction(){
    const description = descriptionInput.value.trim();
    const amount = parseInt(amountInput.value, 10);

    if(!description || !amount || amount <= 0) {
        alert('올바른 내용과 금액을 입력해주세요');
        return;
    }

    const transaction ={
        id: Date.now(),
        description: description,
        amount: amount,
        type: currentTransactionType,
        createdAt: new Date(),
    };

    transactions.unshift(transaction);
    descriptionInput.value= "";
    amountInput.value = "";

    saveTransactions();
    render();
}

function deleteTransaction(id){
    let newTransactions = [];
    for(let transaction of transactions) {
        if(transaction.id === id) {
            continue;
        }
        newTransactions.push(transaction);
    }

    transactions = newTransactions;
    saveTransactions();
    render();
}

function getFilteredTransactions(){
    const filteredTransactions = [];
    if(filterState === 'income' || filterState === 'money-in') {
        for(let transaction of transactions){
            if(transaction.type === 'income'){
                filteredTransactions.push(transaction);
            }
        }
        return filteredTransactions;
    } else if(filterState === 'expense' || filterState === 'money-out'){
        for(let transaction of transactions){
            if(transaction.type === 'expense'){
                filteredTransactions.push(transaction);
            }
        }
        return filteredTransactions;
    } else {
        return transactions;
    }
}

function saveTransactions() {
    localStorage.setItem('transactions', JSON.stringify(transactions));
}

function render() {
    transactionList.innerHTML = "";

    const filteredTransactions = getFilteredTransactions();

    if(filteredTransactions.length === 0) {
        emptyStateRender();
    } else {
        filteredTransactions.forEach(function(transaction){
            transactionItemRender(transaction);
        })
    }

    updateAccount();
}

function emptyStateRender(){
    const emptyEl = document.createElement('div');
    emptyEl.className = 'empty-state';
    emptyEl.innerHTML = '내역이 없습니다'
    transactionList.appendChild(emptyEl);
}

function transactionItemRender(transaction){
    const item = document.createElement('li');
    item.className = 'transaction-item';

    const isIncome = transaction.type === 'income';
    const sign = isIncome ? '+' : '-';
    const amountClass = isIncome ? 'income-color' : 'expense-color';
    const formattedDate = new Date(transaction.createdAt).toLocaleDateString('ko-KR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });

    item.innerHTML = `<div class="info">
                        <span class="date">${formattedDate}</span>
                        <span class="description">${transaction.description}</span>
                      </div>
                      <div class="details">
                        <span class="amount ${amountClass}">${sign}${transaction.amount.toLocaleString()}원</span>
                        <button class="delete-btn">삭제</button>
                      </div>`;
    const deleteBtn = item.querySelector('.delete-btn');
    deleteBtn.addEventListener('click', function(){
        deleteTransaction(transaction.id);
    });        
    
    transactionList.appendChild(item);
}

function updateAccount() {
    const totalIncome = transactions
        .filter(t => t.type === 'income')
        .reduce((sum, t) => sum + t.amount, 0);

    const totalExpense = transactions
        .filter(t => t.type === 'expense')
        .reduce((sum, t) => sum + t.amount, 0);

    const balance = totalIncome - totalExpense;

    balanceDisplay.textContent = `${balance.toLocaleString()}원`;

    if (accountContainer) {
        accountContainer.innerHTML = `<div class="account-item">
                                        <span>총 수입</span> <br>
                                        <strong class="income-color">${totalIncome.toLocaleString()}원</strong>
                                      </div>
                                      <div class="account-item">
                                        <span>총 지출</span> <br>
                                        <strong class="expense-color">${totalExpense.toLocaleString()}원</strong>
                                      </div>
                                      <div class="account-item">
                                        <span>잔액</span> <br>
                                        <strong>${balance.toLocaleString()}원</strong>
                                      </div>`;
    }
}

function setFilter(filter) {
    filterState = filter;
    filterBtns.forEach(function(btn){
        btn.classList.toggle('active', btn.dataset.filter === filter);
    });
    render();
}

function setTransactionType(type){
    currentTransactionType = type;
    incomeBtn.classList.toggle('active', type === 'income');
    expenseBtn.classList.toggle('active', type === 'expense');
}

document.addEventListener('DOMContentLoaded', init);