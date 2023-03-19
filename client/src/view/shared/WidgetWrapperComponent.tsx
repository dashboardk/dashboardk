import { WidgetInfo } from "../models/WidgetInfo";

interface WidgetWrapperComponentProps {
  widget: WidgetInfo
}


export default function WidgetWrapperComponent(props: WidgetWrapperComponentProps) {

  return (
    <div>
      {getWidgetFromType(props.widget.type)}
    </div>
  );
}

function getWidgetFromType(type: String) {
  if(type === 'CommitCount') {
    return ('Commit Count')
  }

  return 'UnRecognized type'
}
