import React from 'react'

const MainList = () => {
  return (
    <>
        <div className='container'>

            <h1>Expense Tracker</h1>

            <div className='panel left-panel controls'>

            </div>

            <div className='panel right-panel content'>

                <div className="balance">
                    <h1></h1>
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