import { gql, useQuery } from '@apollo/client';
import CircularProgress from '@mui/material/CircularProgress';
import React from 'react';

interface Props {
    data: String;
}

export default function CommitCountWidgetComponent(props: Props) {
    return (
        <div>
            {props?.data}
        </div>
    );
}

