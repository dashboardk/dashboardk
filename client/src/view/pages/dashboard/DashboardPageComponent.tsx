import { Container, Grid } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import ReactGridLayout from 'react-grid-layout';
import { WidgetInfo } from '../../models/WidgetInfo';
import LoadingComponent from '../../shared/LoadingComponent';
import NavBarContainer from "../../shared/navbar/NavBarContainer";
import WidgetWrapperComponent from '../../shared/WidgetWrapperContainer';

interface DashboardPageProps {
    widgets: WidgetInfo[] | null
    loading: boolean | null
}


export default function DashboardPageComponent(props: DashboardPageProps) {

    return (
        <div>
            <NavBarContainer />
            <Container component="main" maxWidth="sm">
                <CssBaseline />
                {getLoadingComponent(props.loading)}
                {getWidgetsComponents(props.widgets)}
            </Container>
        </div>
    );
}

function getLoadingComponent(loading: boolean | null) {
    if (loading) {
        return <LoadingComponent />
    } else {
        return <div />
    }
}


function getWidgetsComponents(widgets: WidgetInfo[] | null) {
    if (widgets == null) {
        return <div/>
    } else {
        return <ReactGridLayout layout={generateLayout(widgets)}>
            {widgets.map((element, index) => {
                return <div key={index}>
                    <WidgetWrapperComponent widget={element} />
                </div>
            })}
        </ReactGridLayout>
    }
}

function generateLayout(widgets: WidgetInfo[]) {
    return widgets.map((item, index) => {
      const y = Math.ceil(Math.random() * 4) + 1;
      return {
        x: (index * 2) % 12,
        y: Math.floor(index / 6) * y,
        w: 2,
        h: y,
        i: index.toString()
      };
    });
  }