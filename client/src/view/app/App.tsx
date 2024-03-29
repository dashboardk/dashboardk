import { ApolloClient, ApolloProvider, InMemoryCache } from '@apollo/client';
import DashboardPageContainer from '../pages/dashboard/DashboardPageContainer';
import './App.css';

function App() {

  const client = new ApolloClient({
    uri: "/graphql",
    cache: new InMemoryCache()
  });

  return (
    <ApolloProvider client={client}>
      <div className="App">
        <DashboardPageContainer />
      </div>
    </ApolloProvider>
  );
}

export default App;
