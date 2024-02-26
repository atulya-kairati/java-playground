import {
  Button,
  ButtonGroup,
  Heading,
  Stack,
  Card,
  CardFooter,
  CardBody,
  Text,
  Divider,
  Image,
  Center,
  Spacer,
} from "@chakra-ui/react";
import CustomerDeletionDialog from "./CustomerDeletionDialog";
import { deleteCustomerById } from "../services/client";
import {
  errorNotification,
  successNotification,
} from "../services/notification";
import UpdateCustomerButton from "./UpdateCustomerButton";

export function CardWithImage({
  id,
  name,
  email,
  age,
  gender,
  fetchCustomers,
}) {
  const nameLen = 14;

  name ??= "Manus Chaubey";

  name = name.length > nameLen ? name.slice(0, nameLen) + "..." : name;

  const profileImage = `https://randomuser.me/api/portraits/med/${
    gender == "female" ? "women" : "men"
  }/${id % 100}.jpg`;

  function onDeleteCustomer(id) {
    deleteCustomerById(id)
      .catch((err) => {
        errorNotification("Couldn't delete cutomers", err.message);
      })
      .then((status) => {
        if (status === 200)
          successNotification("Success", `Deleted customer: ${name}`);
      })
      .finally(() => fetchCustomers());
  }

  return (
    <Card minW={240} maxW={240} height={300} margin={4}>
      <Stack>
        <UpdateCustomerButton
          id={id}
          name={name}
          email={email}
          age={age}
          gender={gender}
          fetchCustomers={fetchCustomers}
        />

        <CardBody>
          <Center>
            <Image
              src={profileImage}
              alt="Profile Picture"
              borderRadius="full"
              boxSize={24}
            />
          </Center>

          <Center>
            <Stack mt="6" spacing="3">
              <Heading size="md">{name}</Heading>
              <Text>Age: {age}</Text>
            </Stack>
          </Center>
        </CardBody>
      </Stack>
      <Divider />
      <CardFooter flexWrap="wrap" spacing="space-between">
        <Button variant="solid" colorScheme="blue" flex="1">
          View
        </Button>
        <Spacer />
        <CustomerDeletionDialog
          name={name}
          onDelete={() => onDeleteCustomer(id)}
        />
      </CardFooter>
    </Card>
  );
}
