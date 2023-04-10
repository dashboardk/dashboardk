import { gql, useQuery } from '@apollo/client';
import CircularProgress from '@mui/material/CircularProgress';
import React from 'react';

interface Props {
    data: String;
}

export default function CommitCountWidgetComponent(props: Props) {
    const commitCountData: CommitCountData = JSON.parse(props.data.toString());

    return (
        <div>
            {commitCountData.noOfCommits}
            {commitCountData.commits?.slice(0,3).map((element, index) => {
                return <div key={index}>
                    <CommitInfoItemComponent commit={element}/>
                </div>
            })}
        </div>
    );
}

interface CommitInfoItemComponentProps {
    commit: CommitInfo
}

function CommitInfoItemComponent(commitInfoProps: CommitInfoItemComponentProps) {
    return <div>
        {commitInfoProps.commit.message}-{commitInfoProps.commit.author}
    </div>
}


interface CommitCountData {
    noOfCommits: number,
    commits: CommitInfo[]
}

interface CommitInfo {
    message: string,
    author: string
}
