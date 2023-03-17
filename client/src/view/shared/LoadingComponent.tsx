import CircularProgress from '@mui/material/CircularProgress';
import React from 'react';

export default function LoadingComponent() {

    return (
        <div>
            <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
                <CircularProgress/>
            </div>
        </div>
    );
}

