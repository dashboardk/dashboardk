import { Container, Grid } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import { WidgetInfo } from '../../models/WidgetInfo';
import LoadingComponent from '../../shared/LoadingComponent';
import NavBarContainer from "../../shared/navbar/NavBarContainer";
import WidgetWrapperComponent from '../../shared/WidgetWrapperComponent';

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
        return <Grid container>
            {widgets.map((item, index) => {
                return <Grid item>
                    <WidgetWrapperComponent widget={item} />
                </Grid>
            })}
        </Grid>
    }
}