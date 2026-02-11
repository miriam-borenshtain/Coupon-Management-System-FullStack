import Category from "./Category";
import {useRef, useState} from "react"
import '../newItem.css';


const NewCoupon = (props) => {

    const companyRef = useRef("")
    const titleRef = useRef("")
    const descriptionRef = useRef("")
    const startDateRef = useRef(null)
    const endDateRef = useRef(null)
    const amountRef = useRef(0)
    const priceRef = useRef(0)
    const imageRef = useRef("")  

    const [category, setCategory] = useState(0);

    const submitHanler = (event) => {
        event.preventDefault();
        console.log(category)
        const details = { company: companyRef.current.value, category:category, title: titleRef.current.value, description: descriptionRef.current.value, startDate: startDateRef.current.value, 
            endDate: endDateRef.current.value, amount: amountRef.current.value, price: priceRef.current.value, image: imageRef.current.value }
        console.log("details: " + details);
        props.addCoupon(details);

    }

    return (
            <form className="form">
                <div className="input-container ic2">
                    <input id="title" class="input" placeholder=" " type='text' ref={titleRef} />
                    <label for="title" class="placeholder">Coupon title</label>
                </div>
                <div className="input-container ic2">
                    <textarea id="description" class="input" placeholder=" " ref={descriptionRef} />
                    <label for="description" class="placeholder">Description</label>
                </div>
                <div className="input-container ic2">
                    <input id="sdate" class="input" placeholder=" " type='date' ref={startDateRef} />
                    <label for="sdate" class="placeholder">Start date</label>
                </div>
                <div className="input-container ic2">
                    <input id="edate" class="input" placeholder=" " type='date' ref={endDateRef} />
                    <label for="edate" class="placeholder">End date</label>
                </div>
                <div className="input-container ic2">
                    <input id="amount" class="input" placeholder=" " type='number' ref={amountRef} />
                    <label for="amount" class="placeholder">Amount</label>
                </div>
                <div className="input-container ic2">
                    <input id="price" class="input" placeholder=" " type='number' ref={priceRef} />
                    <label for="price" class="placeholder">Price</label>
                </div>
                <div className="input-container ic2">
                    <input id="image" class="input" placeholder=" " type='file' ref={imageRef} accept="image/png, image/jpeg"/>
                    <label for="image" className="placeholder">Image</label>
                </div>
                <Category setCategory={setCategory} />
                <button className="add-item" type="submit" onClick={submitHanler}>Add new coupon</button>
            </form>
    )
}

export default NewCoupon;