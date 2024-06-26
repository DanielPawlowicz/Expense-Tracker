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

    // Get expenses value from specific month
    getSpecificMonthValue(m, y){
        return axios.get(API_URL + `expensesSpecificMonthSummary?month=${m}&year=${y}`)
    }

    // Delete expense
    deleteExpense(id){
        return axios.delete(API_URL + "/deleteExpense/"+id)
    }

    // Save expense
    saveExpense(expense){
        return axios.post(API_URL + "/addExpense", expense);
    }

    //Edit expense
    editExpense(id, expense){
        return axios.put(API_URL + "/editExpense/" + id, expense)
    }

    //export excel
    excelExport(y, m){
        return axios.get(API_URL + `/exportFromSpecificMonth?year=${y}&month=${m}`, {responseType: 'blob'});
    }

}

export default new ExpenseService;
