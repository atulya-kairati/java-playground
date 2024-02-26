import { EditIcon } from "@chakra-ui/icons";
import { IconButton, Input } from "@chakra-ui/react";

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
import UpdateCustomerForm from "./UpdateCustomerForm";

export default function UpdateCustomerButton({
  id,
  name,
  email,
  age,
  gender,
  fetchCustomers,
}) {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <>
      <IconButton
        aria-label="Edit"
        icon={<EditIcon />}
        isRound={true}
        position="absolute"
        top={2}
        right={2}
        onClick={onOpen}
      />

      <Drawer isOpen={isOpen} placement="right" onClose={onClose}>
        <DrawerOverlay />
        <DrawerContent>
          <DrawerCloseButton />
          <DrawerHeader>Update Customer</DrawerHeader>

          <DrawerBody>
            <UpdateCustomerForm
              id={id}
              name={name}
              mail={email}
              age={age}
              gender={gender}
              fetchCustomers={fetchCustomers}
              onClose={onClose}
            />
          </DrawerBody>

          <DrawerFooter>
            <Button variant="outline" mr={3} onClick={onClose}>
              Cancel
            </Button>
          </DrawerFooter>
        </DrawerContent>
      </Drawer>
    </>
  );
}
