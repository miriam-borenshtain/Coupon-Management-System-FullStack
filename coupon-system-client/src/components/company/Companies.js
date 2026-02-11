import { Fragment } from "react"
import CompanyItem from "./CompanyItem";
import NewCompany from "./NewCompany";
import '../viewItem.css';


const Companies = (props) => {
    return(
        <Fragment>
        <NewCompany addCompany={props.addCompany}/>
        <div className="exist-items">Existing Companies
            {props.items.map(company => {
                return (
                    <CompanyItem key={company.id} id={company.id} name={company.name}
                            email={company.email} password={company.password} deleteCompany={props.deleteCompany}/>
             )}) }</div>
        </Fragment>
    )
}

export default Companies;