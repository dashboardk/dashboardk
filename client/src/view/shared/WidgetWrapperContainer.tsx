import { gql, useQuery } from "@apollo/client";
import { Card } from "@mui/material";
import { WidgetInfo } from "../models/WidgetInfo";
import LoadingComponent from "./LoadingComponent";
import CommitCountWidgetComponent from "./widgets/CommitCountWidgetComponent";

interface WidgetWrapperContainerProps {
  widget: WidgetInfo
}


export default function WidgetWrapperContainer(props: WidgetWrapperContainerProps) {

  const widgetDataQuery = gql`
    {
      widgetData(widgetName: "${props.widget.name}") 
    }
    `;

    const { data, loading, error } = useQuery(widgetDataQuery);

  return (
    <Card>
      {getWidgetFromResponse(props.widget.name, props.widget.type, data, loading, error)}        
    </Card>
  );
}

function getWidgetFromResponse(name: String, type: String, data: any, loading: any, error: any) {
  if(loading) {
    return <LoadingComponent />
  }
  if(error) {
    return <div>{error?.message}</div>
  }
  if(data) {
      return <div>
        {name}
        {getWidgetFromType(type, data.widgetData)}
        </div>
  }
}

function getWidgetFromType(type: String, data: String) {
  if(type === 'CommitCount') {
    return <CommitCountWidgetComponent data={data} />
  }

  return <div>'UnRecognized type'</div>
}
