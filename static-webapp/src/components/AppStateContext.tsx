import React, { ReactNode, createContext, useContext, useReducer } from 'react';

// Tworzenie kontekstu
const AppStateContext = createContext({ } as AppContext);

// Definicja reducer'a
const appReducer = (state : AppState, action: AppEvent): AppState => {
  switch (action.type) {
    case 'APP_EVENT_1':
      // Logika dla akcji typu 1
      return { ...state, /* zaktualizowane wartości stanu */ };
    case 'APP_EVENT_2':
      // Logika dla akcji typu 2
      return { ...state, /* zaktualizowane wartości stanu */ };
    default:
      return state;
  }
};

// Komponent dostarczający kontekstu
interface AppStateProviderProps {
    children: ReactNode
}
export const AppStateProvider: React.FC<AppStateProviderProps> = props => {
  const [state, dispatch] = useReducer(appReducer, { });

  return (
    <AppStateContext.Provider value={{ state, dispatch }}>
      {props.children}
    </AppStateContext.Provider>
  );
};

// Hook do użycia stanu i dispatcha
export const useAppState = () => {
  const context = useContext(AppStateContext);
  if (!context) {
    throw new Error('useAppState musi być użyte wewnątrz AppStateProvider');
  }
  return context;
};

type AppContext = { 
  state: AppState
  dispatch: React.Dispatch<AppEvent>
}

type AppState = {
}

type AppEvent1 = {
    type: 'APP_EVENT_1'
}

type AppEvent2 = {
    type: 'APP_EVENT_2'
}

export type AppEvent = AppEvent1 | AppEvent2;