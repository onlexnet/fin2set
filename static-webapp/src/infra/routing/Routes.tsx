import {
    createBrowserRouter,
    createRoutesFromElements,
    Route
  } from "react-router-dom";
import { ChatRoute} from "../../views/chat/Chat.Route";
import { MainView } from "../../views/main/Main.View";
import { UploadView } from "../../components/upload/UploadView";

export const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path="/" element={<MainView />}>
        <Route path="chat" element={<ChatRoute />} />
        <Route path="bank" element={<UploadView />} />
      </Route>
    )
  );
