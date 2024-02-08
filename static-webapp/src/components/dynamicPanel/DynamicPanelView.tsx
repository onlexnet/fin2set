import { Label, Stack } from "@fluentui/react";
import { useViewSubscription, View } from "../../api/gql/graphql";
import { UploadView } from "../upload/UploadView";

export const DynamicPanelView: React.FC<{}> = props => {

    const { data: currentView } = useViewSubscription({})
  
    console.log(`currentView: ${ JSON.stringify(currentView)}`)
    if (currentView?.view === View.View1) {
        return (
            <UploadView />);
        }
    else {
        return (
            <Stack.Item>
                <Label>Placeholder for dynamic view</Label>
            </Stack.Item>);
        }
}
