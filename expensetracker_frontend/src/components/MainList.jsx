import React, { useEffect, useState } from 'react';
import ExpenseService from '../service/ExpenseService';

const MainList = () => {

    const [thisMBalance, setThisMBalance] = useState(0);
    const [allExpensesList, setAllExpensesList] = useState([]);

    // fetch the balance from this month when site is loading
    useEffect(() => {
        getBalance();
        getAllExpenses();
    }, []);

    // get the balance from this month
    const getBalance = () => {
        ExpenseService.getThisMonthBalance().then(res => {
            setThisMBalance(res.data);
        }).catch(err => {
            console.error("Error fetching balance: ", err);
        });
    };

    // get all expenses
    const getAllExpenses = () => {
        ExpenseService.getAllExpenses().then((res) => {
            setAllExpensesList(res.data);
            console.log(allExpensesList);
        }).catch((err) => {
            console.error("Error fetching all expenses: ", err);
        });
    };


  return (
    <>
        <div className='container'>

            <h1>Expense Tracker</h1>

            <div className='panel left-panel controls'>

            </div>

            <div className='panel right-panel content'>

                <div className="balance">
                    <h1>{thisMBalance}</h1>
                </div>

                <div className='new-expense'>
                    <form>

                    </form>
                </div>

                <div className="expense-list">
                    {

                    }
                </div>
            </div>
        </div>
    </>
  )
}

export default MainList