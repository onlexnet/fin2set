import {
    createBrowserRouter,
    createRoutesFromElements,
    Route
  } from "react-router-dom";
import { ChatRoute} from "../../views/chat/Chat.Route";
import { MainView } from "../../views/main/Main.View";
import { PlaidLoginView } from "../../components/plaid/PlaidLoginView";

export const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path="/" element={<MainView />}>
        <Route path="chat" element={<ChatRoute />} />
        <Route path="bank" element={<PlaidLoginView />} />
      </Route>
    )
  );
