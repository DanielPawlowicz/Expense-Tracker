import React, { useEffect, useState } from 'react';
import ExpenseService from '../service/ExpenseService';
import { IconButton } from '@mui/material';
// icons
import DeleteIcon from '@mui/icons-material/Delete'; // delete
import EditNoteIcon from '@mui/icons-material/EditNote'; // edit
import CottageIcon from '@mui/icons-material/Cottage'; // housing
import BathtubIcon from '@mui/icons-material/Bathtub'; // utilities
import RestaurantIcon from '@mui/icons-material/Restaurant'; // food
import DirectionsCarIcon from '@mui/icons-material/DirectionsCar'; // transportation
import LocalHospitalIcon from '@mui/icons-material/LocalHospital'; // health
import AccessibilityIcon from '@mui/icons-material/Accessibility'; // personal
import SportsEsportsIcon from '@mui/icons-material/SportsEsports'; // entertainment
import MoreHorizIcon from '@mui/icons-material/MoreHoriz'; // other






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

    // convert month number to month name
    const getMonthName = (month) => {
        const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
        return months[month - 1];
    };

    // group expenses by month and day
    const groupExpensesByMonthAndDay = () => {
        const groupedExpenses = {};
        allExpensesList.forEach(expense => {
            const expenseDate = new Date(expense.expenseDate);
            const month = getMonthName(expenseDate.getMonth() + 1); // Adding 1 to get the correct month index
            const day = expenseDate.getDate(); // Get day number
            if (!groupedExpenses[month]) {
                groupedExpenses[month] = {
                    totalValue: 0,
                    days: {}
                };
            }
            if (!groupedExpenses[month].days[day]) {
                groupedExpenses[month].days[day] = [];
            }
            groupedExpenses[month].days[day].push(expense);
            groupedExpenses[month].totalValue += expense.expenseValue;
        });
        return groupedExpenses;
    };


    // get the icon component based on the expense type
    const getExpenseIcon = (expenseType) => {
        switch (expenseType) {
            case 'HOUSING':
                return <CottageIcon />;
            case 'UTILITIES':
                return <BathtubIcon />;
            case 'FOOD':
                return <RestaurantIcon />;
            case 'TRANSPORTATION':
                return <DirectionsCarIcon />;
            case 'HEALTH':
                return <LocalHospitalIcon />;
            case 'PERSONAL':
                return <AccessibilityIcon />;
            case 'ENTERTAINMENT':
                return <SportsEsportsIcon />;
            case 'OTHER':
                return <MoreHorizIcon />;
            default:
                return <MoreHorizIcon />;
        }
    };

    // deleting expense
    const deleteExpense = async (id) => {
        try{
            ExpenseService.deleteExpense(id);
            getBalance();
            getAllExpenses();
            window.location.reload();
        } catch(err){
            console.error("Error deleting expense: " + err);
        }
    }

    // expenses grouped by month and day
    const renderExpensesByMonthAndDay = () => {
        const groupedExpenses = groupExpensesByMonthAndDay();
        return Object.entries(groupedExpenses).map(([month, data]) => (
            <div key={month} className='month-container'>
                <h2 className='month-name'>{month} <span className='monthly-value'>{data.totalValue} <span className='zl'>zł</span></span></h2>
                {Object.entries(data.days)
                    .sort(([a], [b]) => b - a) // Sort the days in descending order
                    .map(([day, expenses]) => (
                        <div key={day} className='day-container'>
                            <h3 className='day-name'>{day}</h3>
                            <table>
                                <tbody>
                                    {expenses.map(exp => (
                                        <tr key={exp.id} className='single-expense'>
                                            <td className='e-value'><b>{exp.expenseValue} </b><span>zł</span></td>
                                            <td className='e-type'>{getExpenseIcon(exp.expenseType)}</td>
                                            <td className='e-descr'>{exp.description}</td>
                                            <td className='controls'>
                                                <IconButton className='icnbtn'>
                                                    <EditNoteIcon className='icon' />
                                                </IconButton>
                                                
                                                <IconButton className='icnbtn'onClick={() => deleteExpense(exp.id)} >
                                                    <DeleteIcon className='icon'/>
                                                </IconButton>
                                                
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    ))
                }
            </div>
        ));
    };



  return (
    <>
        <div className='container'>
            <div className="heading">
                <h3 className='title'>This month expenses</h3>
                <h1 className='this-month-balance'>{thisMBalance} <span>zł</span></h1>
            </div>
            <div className="inside-container">

                <div className='panel left-panel controls'>
                    {/* <p>ajdfssssssssssssssssssssssssssssssssssk</p> */}
                </div>

                <div className='panel right-panel content'>

                    <div className='new-expense'>
                        <form>

                        </form>
                    </div>

                    <div className="expense-list">
                        {renderExpensesByMonthAndDay()}
                    </div>
                </div>
            </div>
        </div>
    </>
  )
}

export default MainList