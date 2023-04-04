import { Container, Grid } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import ReactGridLayout, { Layout, Layouts } from 'react-grid-layout';
import { WidgetInfo } from '../../models/WidgetInfo';
import LoadingComponent from '../../shared/LoadingComponent';
import NavBarContainer from "../../shared/navbar/NavBarContainer";
import WidgetWrapperComponent from '../../shared/WidgetWrapperContainer';
import { Responsive, WidthProvider } from 'react-grid-layout';

const ResponsiveGridLayout = WidthProvider(Responsive);

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
        return <div />
    } else {
        return <ResponsiveGridLayout
            className="layout" layouts={getLayouts(widgets)}
        >
            {widgets.map((element, index) => {
                return <div key={index}>
                    <WidgetWrapperComponent widget={element}/>
                </div>
            })}
        </ResponsiveGridLayout>
    }
}

function getLayouts(widgets: WidgetInfo[]): Layouts {
    const layouts: Layouts = {} as Layouts;
    layouts[0] = [];
    widgets.forEach((element, index) => {
        layouts[0].push({ i: index.toString(), x: 0, y: 0, w: 1, h: 1 },
        );
    });
    return layouts;
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