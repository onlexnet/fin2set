export const ChatFrame: React.FC<{}> = props => {

    const source = "https://web.powerva.microsoft.com/environments/Default-29084a59-db89-4eb9-908a-53f42318c77d/bots/cre80_financialStatements/webchat?__version__=2";
    return (<iframe src={source} frameBorder="0" style={{width: "800px",  height: "100vh"}} />);
    
}
