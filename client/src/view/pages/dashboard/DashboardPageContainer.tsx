import DashboardPageComponent from "./DashboardPageComponent";
import { gql, useQuery } from "@apollo/client";

export default function DashboardPageContainer() {


    const widgetsQuery = gql`
    {
          metaInfo {
                widgets {
                          name
                          type
                        }
                    }
                }
    `;

    const { data, loading, error } = useQuery(widgetsQuery);


    return (
        <DashboardPageComponent
            widgets={data?.metaInfo?.widgets}
            loading={loading}
        />
    );
}
