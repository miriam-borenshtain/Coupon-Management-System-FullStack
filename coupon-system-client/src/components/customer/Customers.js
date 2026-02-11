import { Fragment } from "react"
import CustomerItem from "./CustomerItem";
import NewCustomer from "./NewCustomer";


const Customers = (props) => {
    return(
        <Fragment>
        <NewCustomer addCustomer={props.addCustomer}/>
        <div className="exist-items">Existing Customers</div>
            {props.items.map(customer => {
                return (
                    <CustomerItem key={customer.id} id={customer.id} fname={customer.firstName} lname={customer.lastName}
                            email={customer.email} password={customer.password} deleteCustomer={props.deleteCustomer}/>
             )}) }
        </Fragment>
    )
}

export default Customers;