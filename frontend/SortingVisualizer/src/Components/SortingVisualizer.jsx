import React, { useState } from 'react';
import SortingMenu from './SortingMenu';
import InputForm from './InputForm';
import SortingSteps from './SortingSteps';

const SortingVisualizer = () => {
    const [selectedSort, setSelectedSort] = useState('');
    const [steps, setSteps] = useState([]);
    const [currentStep, setCurrentStep] = useState(0);

    const handleSort = async (numbers) => {
        // Call the API to get the sorted steps based on selectedSort
        const response = await fetch(`http://localhost:8080/api/sort/${selectedSort}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(numbers),
        });
        
        const data = await response.json();
        setSteps(data); // assuming your API returns an array of steps
        setCurrentStep(0);
    };

    return (
        <div className="sorting-visualizer">
            <SortingMenu selectedSort={selectedSort} setSelectedSort={setSelectedSort} />
            <InputForm onSubmit={handleSort} />
            <SortingSteps steps={steps} currentStep={currentStep} setCurrentStep={setCurrentStep} />
        </div>
    );
};

export default SortingVisualizer;
