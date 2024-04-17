import classes from "./input.module.scss"

const Input = ({name, label, type, onChange: handleOnChange}) => {
    return (
        <>
            <label htmlFor={name} className={classes["Input"]}>
                {label}
                <input name={name} type={type} onChange={handleOnChange}/>
            </label>
        </>
    )
}

export default Input