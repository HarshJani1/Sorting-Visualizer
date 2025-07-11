import React from 'react';

const SortingMenu = ({ selectedSort, setSelectedSort }) => {
    const sortingAlgorithms = ['bubble', 'selection', 'insertion','heap', 'merge', 'quick','bucket'];

    return (
        <div className="sorting-menu">
            <h2>Select Sorting Algorithm</h2>
            {sortingAlgorithms.map((algorithm, index) => (
                <button
                    key={index}
                    onClick={() => setSelectedSort(algorithm)}
                    className={selectedSort === algorithm ? 'selected' : ''}
                >
                    {algorithm}
                </button>
            ))}
        </div>
    );
};

export default SortingMenu;
