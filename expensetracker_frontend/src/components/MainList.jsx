import React, { useEffect, useState } from 'react';
import ExpenseService from '../service/ExpenseService';

const MainList = () => {

    const [thisMBalance, setThisMBalance] = useState(0);

    useEffect(() => {
        getBalance();
    }, []);

    const getBalance = () => {
        ExpenseService.getThisMonthBalance().then(res => {
            setThisMBalance(res.data);
        }).catch(err => {
            console.error("Error fetching balance: ", err);
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

                </div>
            </div>
        </div>
    </>
  )
}

export default MainList