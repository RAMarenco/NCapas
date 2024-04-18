import classes from "./input.module.scss";

const Input = ({ name, label, type, value, placeholder, onChange: handleOnChange }) => {
    return (
        <>
            <label htmlFor={name} className={classes["Input"]}>
                {label}
                <input
                    name={name}
                    value={value}
                    type={type}
                    placeholder={placeholder}
                    onChange={handleOnChange}
                />
            </label>
        </>
    );
};

export default Input;
