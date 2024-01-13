import React from 'react';
import { useAppState } from './AppStateContext';

const ExampleComponent: React.FC<{}> = props => {
  const { state, dispatch } = useAppState();

  const handleAction = () => {
    // Wywołanie dispatch z akcją typu 1
    dispatch({ type: 'APP_EVENT_1'});
  };

  return (
    <div>
      {/* Wyświetlanie stanu */}
      <p>Stan aplikacji: {JSON.stringify(state)}</p>
      {/* Przycisk do wywołania akcji */}
      <button onClick={handleAction}>Wykonaj Akcję</button>
    </div>
  );
};
