import axios from "axios";

const API_URL = "http://localhost:8080";

class ExpenseService {

    // Get this month balance value
    getThisMonthBalance () {
        return axios.get(API_URL + "/expensesThisMonthSummary");
    }

    // Get all expenses display
    getAllExpenses(){
        return axios.get(API_URL + "/allExpensesOrdered");
    }

}

export default new ExpenseService;
