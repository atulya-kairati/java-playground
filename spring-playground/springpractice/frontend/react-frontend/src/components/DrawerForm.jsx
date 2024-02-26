import {
  Button,
  useDisclosure,
  Drawer,
  DrawerOverlay,
  DrawerContent,
  DrawerCloseButton,
  DrawerHeader,
  DrawerBody,
  DrawerFooter,
} from "@chakra-ui/react";
import React from "react";
import CreateCustomerForm from "./CreateCustomerForm";

const AddIcon = () => "+";

export function DrawerForm({ fetchCustomers }) {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <>
      <Button
        variant="solid"
        colorScheme="blue"
        leftIcon={<AddIcon />}
        onClick={onOpen}
      >
        Create Customer
      </Button>

      <Drawer isOpen={isOpen} placement="right" onClose={onClose} size="md">
        <DrawerOverlay />
        <DrawerContent>
          <DrawerCloseButton />
          <DrawerHeader>Create new customer</DrawerHeader>

          <DrawerBody>
            <CreateCustomerForm
              fetchCustomers={fetchCustomers}
              onClose={onClose}
            />
          </DrawerBody>

          <DrawerFooter>
            <Button colorScheme="gray" onClick={onClose}>
              Close
            </Button>
          </DrawerFooter>
        </DrawerContent>
      </Drawer>
    </>
  );
}
