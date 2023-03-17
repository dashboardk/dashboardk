import React, {useEffect, useState} from "react";
import DashboardPageComponent from "./DashboardPageComponent";

export default function DashboardPageContainer() {

    const {error, loading} = useDashboard();


    return (
        <DashboardPageComponent
            loading={loading}
        />
    );
}

function useDashboard() {
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    return {error, loading};
}
