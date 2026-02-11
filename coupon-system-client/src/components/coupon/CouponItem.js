import '../viewItem.css';

const CouponItem = (props) => {

    const deleteCouponHandler = () =>{
        props.deleteCoupon(props.id);
    }
    
    return (
        <div className="item">
            <h3>{props.title}</h3>
            <h3>{props.company}</h3>
            <div>Category: {props.category}</div>
            <div>{props.description}</div>
            <div>{props.startDate.toString()}</div>
            <div>{props.endDate.toString()}</div>
            <div>{props.amount}</div>
            <div>{props.price}</div>
            <div>{props.image}</div>
            <button className="delete"onClick={deleteCouponHandler}><span class="glyphicon glyphicon-trash"></span> delete</button>
            <div>-------</div>
        </div>
    )
}

export default CouponItem;