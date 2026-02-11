import '../newItem.css';
import './category.css';

const Category = (props) => {

    return (
        <div className="choose-category">
            <h4>Coupon category:</h4>
            <label class="rad-label">
                <input type="radio" class="rad-input" name="rad" onClick={e=>props.setCategory(e.target.value)} value="1"/>
                <div class="rad-design"></div>
                <div class="rad-text">Food</div>
            </label>
            <label class="rad-label">
                <input type="radio" class="rad-input" name="rad" onClick={e=>props.setCategory(e.target.value)} value="2"/>
                <div class="rad-design"></div>
                <div class="rad-text">Electricity</div>
            </label>

            <label class="rad-label">
                <input type="radio" class="rad-input" name="rad" onClick={e=>props.setCategory(e.target.value)}  value="3"/>
                <div class="rad-design"></div>
                <div class="rad-text">Restaurant</div>
            </label>

            <label class="rad-label">
                <input type="radio" class="rad-input" name="rad" onClick={e=>props.setCategory(e.target.value)} value="4"/>
                <div class="rad-design"></div>
                <div class="rad-text">Vacation</div>
            </label>

            <label class="rad-label">
                <input type="radio" class="rad-input" name="rad" onClick={e=>props.setCategory(e.target.value)} value="5"/>
                <div class="rad-design"></div>
                <div class="rad-text">Other</div>
            </label>
        </div>

    )
}

export default Category;