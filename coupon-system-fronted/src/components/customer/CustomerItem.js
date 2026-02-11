import '../viewItem.css';

const CompanyItem = (props) => {

    const deleteCustomerHandler = () =>{
        props.deleteCustomer(props.id);
    }

    return (
        <div className="item">
            <h3>{props.fname} {props.lname}</h3>
            <div>{props.email}</div>
            <div>{props.password}</div>
             <button className="delete" onClick={deleteCustomerHandler}><span class="glyphicon glyphicon-trash"></span> delete</button>
            <div>-------</div>
        </div>
    )
}

export default CompanyItem;