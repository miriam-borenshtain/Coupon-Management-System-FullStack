import fetchWrapper from '../helpers/fetchWrapper';
import '../viewItem.css';


const CompanyItem = (props) => {

    const deleteCompanyHandler = () =>{
        fetchWrapper.deleteCompany(props.id)
        props.deleteCompany(props.id);
    }

    return (
        <div className="item">
            <h3>{props.name}</h3>
            <div>{props.email}</div>
            <div>{props.password}</div>
            <button className="delete" onClick={deleteCompanyHandler}><span class="glyphicon glyphicon-trash"></span>Delete</button>
        </div>
    )
}

export default CompanyItem;