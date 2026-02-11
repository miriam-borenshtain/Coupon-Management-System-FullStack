import { Fragment } from "react";
import CouponItem from "./CouponItem";
import NewCoupon from "./NewCoupon";
import '../viewItem.css';


const Coupons = (props) => {
    return(
        <Fragment>
        <NewCoupon addCoupon={props.addCoupon}/>
        <div className="exist-items">Existing Coupons</div>
            {props.items.map(coupon => {
                return (
                    <CouponItem key={coupon.id} id={coupon.id} company={coupon.company} title={coupon.title}
                            category={coupon.category} description={coupon.description} startDate={coupon.startDate}
                            endDate={coupon.endDate} amount={coupon.amount} price={coupon.price} image={coupon.image} deleteCoupon={props.deleteCoupon}/>
             )}) }
        </Fragment>
    )
}

export default Coupons;