import React, { useState } from 'react';

const InputForm = ({ onSubmit }) => {
    const [inputValues, setInputValues] = useState(['']);

    const handleChange = (index, value) => {
        const newValues = [...inputValues];
        newValues[index] = value;
        setInputValues(newValues);
    };

    const handleAddInput = () => {
        if (inputValues.length < 12) {
            setInputValues([...inputValues, '']);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const numbers = inputValues.map(Number).filter(num => !isNaN(num));
        onSubmit(numbers);
    };

    return (
        <div className="input-form">
            <form onSubmit={handleSubmit} >
                <h2>Input Numbers (Max 12)</h2>
                {inputValues.map((value, index) => (
                    <input
                        key={index}
                        type="number"
                        value={value}
                        onChange={(e) => handleChange(index, e.target.value)}
                        placeholder="Enter a number"
                    />
                ))}
                <button type="button" onClick={handleAddInput} className="input-form-btn">Add Number</button>
                <button type="submit" className="input-form-btn">Submit</button>
            </form>
        </div>
    );
};

export default InputForm;
