import React, {useState} from "react";
import DashboardPageComponent from "./DashboardPageComponent";

export default function DashboardPageContainer() {

    const {loading} = useDashboard();


    return (
        <DashboardPageComponent
            loading={loading}
        />
    );
}

function useDashboard() {
    const [loading] = useState(true);
    const [error] = useState('');

    return {error, loading};
}
