

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
    const json = await response.json();
    console.log(json);
}

    return (
        <>
            <Appbar/>
            <h2>Loan Details</h2>
            <table width="80%">
                <thead>
                    <tr>
                        <th width>ID</th>
                        <th>Type</th>
                        <th>Duration</th>
                    </tr>
                </thead>

            </table>
        </>
    )
}

export default ViewLoanDetails;