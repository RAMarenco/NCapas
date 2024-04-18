import classes from "./button.module.scss";

const Button = ({ onSubmit: handleClick, children, type }) => {
    return (
        <button
            onSubmit={handleClick}
            type={type}
            className={classes["Button"]}
        >
            {children}
        </button>
    );
};

export default Button;
