import { useCallback, useRef } from "react"
import fetchWrapper from "../helpers/fetchWrapper";
import '../newItem.css'


const NewCompany = (props) => {


    const nameRef = useRef(null);
    const emailRef = useRef(null);
    const passwordRef = useRef(null);

    const submitHanler = useCallback(async (event) => {
        event.preventDefault();
        const details = { name: nameRef.current.value, email: emailRef.current.value, password: passwordRef.current.value, coupons: [] }
        console.log("details: " + details);
        const res = await fetchWrapper.addCompany(details);
        if (res) { props.addCompany(details); }
    }, [props])


    return (
        <form className="form">
            <h3>Add new Company:</h3>
            <div className="input-container ic2">
                <input id="name" type='text' placeholder=" " className="input" ref={nameRef} />
                {/* <div class="cut"></div> */}
                <label for="name" className="placeholder">Company name</label>
            </div>
            <div className="input-container ic2">
                <input id="email" type='email' placeholder=" " className="input" ref={emailRef} />
                <label for="email" className="placeholder">Email</label>
            </div>
            <div className="input-container ic2">
                <input id="password" type='password' placeholder=" " className="input" ref={passwordRef} />
                <label for="password" className="placeholder">Password</label>
            </div >
            <button className="add-item" type="submit" onClick={submitHanler}>Add new company</button>
        </form>
    )
}

export default NewCompany;