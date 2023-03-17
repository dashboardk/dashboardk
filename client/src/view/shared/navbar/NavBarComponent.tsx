import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import React from 'react';

export default function NavBarComponent() {

    return (
        <div>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6">
                        DashboardK
                    </Typography>
                </Toolbar>
            </AppBar>
        </div>
    );
}




