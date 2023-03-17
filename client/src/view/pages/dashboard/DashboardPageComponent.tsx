import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import LoadingComponent from '../../shared/LoadingComponent';
import NavBarContainer from "../../shared/navbar/NavBarContainer";



interface DashboardPageProps {
    loading: boolean
}


export default function DashboardPageComponent(props: DashboardPageProps) {

    return (
        <div>
            <NavBarContainer/>
            <Container component="main" maxWidth="sm">
                <CssBaseline/>
                {getLoadingComponent(props.loading)}
            </Container>
        </div>
    );
}

function getLoadingComponent(loading: boolean) {
    if (loading) {
        return <LoadingComponent/>
    } else {
        return <div/>
    }
}
