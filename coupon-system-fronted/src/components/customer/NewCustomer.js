import { useState } from "react"


const NewCustomer = (props) => {


    const [details, setDetails] = useState({ firstName: "", lastName: "", email: "", password: "" })

    const onChangeHandlerFirstName = (event) => {
        const newDetails = { ...details, firstName: event.target.value }
        setDetails(newDetails)
        console.log(event.target.value)
    }

    const onChangeHandlerLastName = (event) => {
        const newDetails = { ...details, lastName: event.target.value }
        setDetails(newDetails)
        console.log(event.target.value)
    }

    const onChangeHandlerEmail = (event) => {
        const newDetails = { ...details, email: event.target.value }
        setDetails(newDetails)
        console.log(event.target.value)
    }

    const onChangeHandlerPassword = (event) => {
        const newDetails = { ...details, password: event.target.value }
        setDetails(newDetails)
        console.log(event.target.value)
    }

    const submitHanler = (event) => {
        event.preventDefault();
        console.log("details: " + details);
        props.addCustomer(details);

    }

    <div class="input-container ic2">
        <input id="lastname" class="input" placeholder=" " type="text" />
        <div class="cut"></div>
        <label for="lastname" class="placeholder">Last name</label>
    </div>

    return (
        <form className="form">
            <div class="input-container ic2">
                <input id="firstName" class="input" placeholder=" " type='text' onChange={onChangeHandlerFirstName}></input>
                <label for="firstName" class="placeholder">First name</label>
            </div>
            <div class="input-container ic2">
                <input id="lastname" class="input" placeholder=" " type='text' onChange={onChangeHandlerLastName}></input>
                <label for="lastname" class="placeholder">Last name</label>
            </div>
            <div class="input-container ic2">
                <input id="email" class="input" placeholder=" " type='email' onChange={onChangeHandlerEmail}></input>
                <label for="email" class="placeholder">Email</label>
            </div>
            <div class="input-container ic2">
                <input id="password" class="input" placeholder=" " type='password' onChange={onChangeHandlerPassword}></input>
                <label for="password" class="placeholder">Password</label>
            </div>
            <button className="add-item" type="submit" onClick={submitHanler}>Add new Customer</button>
        </form>
    )
}

export default NewCustomer;