

import Appbar from './Appbar';

const ViewLoanDetails = () => {

async function getLoanDetails(e) {
    e.preventDefault()
    const uname = sessionStorage.getItem("username");
    const response = await fetch("http://localhost:8080/viewLoanDetails", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "user_name": uname
        }
    });
    const loans = await response.json();
    console.log(json);
}

    return (
        <>
            <Appbar/>
            <h2>Loan Details</h2>
            <table width="80%">
                <thead>
                    <tr>
                        <th width>Loan ID</th>
                        <th>Loan Type</th>
                        <th>Duration</th>
                        <th>Card ID</th>
                    </tr>
                    <tr>
                        loans.map((loan,index) ={'>'} (
                            <td>{loa[0]}</td>
                            <td>{loan[1]}</td>
                            <td>{loan[2]}</td>
                            <td>{loan[2]}</td>
                        ))
                    </tr>
                </thead>

            </table>
        </>
    )
}

export default ViewLoanDetails;