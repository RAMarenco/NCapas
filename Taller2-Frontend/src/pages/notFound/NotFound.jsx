import React from 'react'
import classes from './NotFound.module.scss'
import Image from './../../assets/Sadbara.svg'

export default function NotFound() {
    return (
        <>
            <div className={classes["container"]}>
                <div className={classes["not-found-container"]}>
                    <img className={classes["sadbara-img"]} src={Image} alt="Sadbara Logo"/>
                    <div>
                        <h1 className={classes["title"]}>404</h1>
                        <p className={classes["bottom-text"]}>Page Not Found</p>
                    </div>
                </div>
            </div>
        </>
    )
}