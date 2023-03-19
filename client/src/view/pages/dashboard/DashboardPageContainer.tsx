import DashboardPageComponent from "./DashboardPageComponent";
import { gql, useQuery } from "@apollo/client";

export default function DashboardPageContainer() {


    const widgetsQuery = gql`
    {
        launchesPast(limit: 10) {
        id
        mission_name
        }
    }
    `;

    const { data, loading, error } = useQuery(widgetsQuery);


    return (
        <DashboardPageComponent
            widgets={data}
            loading={loading}
        />
    );
}
