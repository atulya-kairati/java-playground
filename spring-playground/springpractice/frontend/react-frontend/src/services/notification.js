import { createStandaloneToast } from "@chakra-ui/react";
const { toast } = createStandaloneToast();

function notification(title, description, status) {
  toast({
    title,
    description,
    status,
    duration: 3000,
    isClosable: true,
  });
}

export function successNotification(title, description) {
  notification(title, description, "success");
}

export function errorNotification(title, description) {
  notification(title, description, "error");
}
