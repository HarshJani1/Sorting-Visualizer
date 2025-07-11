import React from 'react';

const SortingSteps = ({ steps, currentStep, setCurrentStep }) => {
    const handleNext = () => {
        if (currentStep < steps.length - 1) {
            setCurrentStep(currentStep + 1);
        }
    };

    const handlePrev = () => {
        if (currentStep > 0) {
            setCurrentStep(currentStep - 1);
        }
    };

    return (
        <div className="sorting-steps">
            <h2>Sorting Steps</h2>
            {steps.length > 0 && (
                <>
                    <div className="stepsCss">{steps[currentStep].join(', ')}</div>
                    <div className="stepsCss">Step no: {currentStep + 1}</div>
                    <button onClick={handlePrev} disabled={currentStep === 0} className="steps-btn">Previous</button>
                    <button onClick={handleNext} disabled={currentStep === steps.length - 1} className="steps-btn">Next</button>
                </>
            )}
        </div>
    );
};

export default SortingSteps;
