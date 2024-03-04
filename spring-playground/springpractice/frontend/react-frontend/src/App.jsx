import {
  AbsoluteCenter,
  Center,
  Spinner,
  Text,
  Wrap,
  WrapItem,
} from "@chakra-ui/react";
import SidebarWithHeader from "./components/shared/SideBar";
import { useEffect, useState } from "react";
import { getAllCustomers, deleteCustomerById } from "./services/client";
import { CardWithImage } from "./components/CardWithImage";
import { DrawerForm } from "./components/DrawerForm";
import {
  errorNotification,
  successNotification,
} from "./services/notification";

function App() {
  let [data, setDataState] = useState(undefined);
  let [hasError, setHasError] = useState(false);

  function fetchCustomers() {
    getAllCustomers()
      .catch((err) => {
        errorNotification("Couldn't fetch cutomers", err.response.data.msg);
        setHasError(true);
      })
      .then((data) => {
        setDataState(data);
        // console.log(data);
      });
  }

  useEffect(() => {
    fetchCustomers();
    return () => {};
  }, []);

  let content;

  if (hasError) {
    content = (
      <AbsoluteCenter axis="both">
        <Text size="xl">Something went wrong....</Text>
      </AbsoluteCenter>
    );
  } else if (data === undefined) {
    content = (
      <AbsoluteCenter axis="both">
        <Spinner
          thickness="4px"
          speed="0.65s"
          emptyColor="gray.200"
          color="blue.500"
          size="xl"
        />
      </AbsoluteCenter>
    );
  } else if (data.length === 0) {
    content = (
      <AbsoluteCenter axis="both">
        <Text size="xl">No users yet 🫵😂</Text>
      </AbsoluteCenter>
    );
  } else {
    content = (
      <Wrap justify="center">
        {data.map((person, index) => {
          return (
            <WrapItem key={index}>
              <CardWithImage
                id={person.id}
                name={person.name}
                email={person.mail}
                age={person.age}
                gender={person.gender}
                fetchCustomers={fetchCustomers}
              />
            </WrapItem>
          );
        })}
      </Wrap>
    );
  }

  return (
    <SidebarWithHeader>
      <DrawerForm fetchCustomers={fetchCustomers} />
      {content}
    </SidebarWithHeader>
  );
}

export default App;
